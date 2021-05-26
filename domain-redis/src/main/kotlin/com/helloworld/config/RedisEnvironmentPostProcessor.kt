package com.helloworld.config

import org.springframework.boot.SpringApplication
import org.springframework.boot.context.properties.bind.Bindable
import org.springframework.boot.context.properties.bind.Binder
import org.springframework.boot.context.properties.bind.PropertySourcesPlaceholdersResolver
import org.springframework.boot.context.properties.source.ConfigurationPropertySources
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.boot.env.PropertiesPropertySourceLoader
import org.springframework.core.Ordered
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.PropertySource
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.core.io.support.ResourcePatternResolver
import java.io.IOException
import java.util.*

class RedisEnvironmentPostProcessor : EnvironmentPostProcessor, Ordered {
    private val propertySourceLoader: PropertiesPropertySourceLoader = PropertiesPropertySourceLoader()


    override fun postProcessEnvironment(environment: ConfigurableEnvironment?, application: SpringApplication?) {
        var resourceLoader = application!!.resourceLoader
        resourceLoader = resourceLoader ?: DefaultResourceLoader()

        val resourcePatternResolver: ResourcePatternResolver = PathMatchingResourcePatternResolver(resourceLoader)

        var resources = arrayOf<Resource?>()
        try {
            resources = resourcePatternResolver.getResources("classpath*:/application-redis.properties")
        } catch (e: IOException) {
            println(e.message)
        }

        for (resource in resources) {
            if (resource != null) {
                println("resource : ${resource.filename}" )
                println("${resource.description}")
            }
            loadResource(resource!!, environment!!)!!.forEach { property ->
                environment.propertySources.addLast(property)
            }
        }
    }

    private fun loadResource(
        resource: Resource,
        environment: ConfigurableEnvironment
    ): MutableList<PropertySource<*>>? {
        val propertySources = this.propertySourceLoader.load(resource.filename, resource)

        val defaultPropertySources = propertySources
            .filter { getMatchedProfiles(it, environment).isEmpty() }
            .toMutableList()

        val filteredPropertySources = environment.activeProfiles.filter { profile -> propertySources.any { getMatchedProfiles(it, environment).contains(profile) } }
            .flatMap {  profile ->
                propertySources.filter { getMatchedProfiles(it, environment).contains(profile) }
            }.toMutableList()

        filteredPropertySources.addAll(defaultPropertySources)
        return filteredPropertySources
    }

    private fun getMatchedProfiles(
        propertySource: PropertySource<*>?,
        environment: ConfigurableEnvironment
    ): Array<String> {
        val binder =
            Binder(ConfigurationPropertySources.from(propertySource), PropertySourcesPlaceholdersResolver(environment))
        return binder.bind("spring.config.activate.on-profile", Bindable.of(Array<String>::class.java))
            .orElse(emptyArray())

    }

    override fun getOrder(): Int = Ordered.LOWEST_PRECEDENCE
}