package com.ranefare.plancatalogservice.controllers.converters

interface ResourceConverter<in D, out R> {

    fun convert(domain: D): R
}