package com.ranefare.plancatalogservice.db.converters

interface EntityConverter<in D, out E> {

    fun convert(domain: D): E
}
