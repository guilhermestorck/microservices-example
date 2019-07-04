package com.ranefare.plancatalogservice.controllers

import com.ranefare.plancatalogservice.contract.contracts.CrudInsuranceCoverageItemContract
import com.ranefare.plancatalogservice.contract.domains.GetAllResponse
import com.ranefare.plancatalogservice.contract.domains.resources.InsuranceCoverageItemResource
import com.ranefare.plancatalogservice.controllers.assemblers.InsuranceCoverageItemResourceToInsuranceCoverageItemAssembler
import com.ranefare.plancatalogservice.controllers.converters.InsuranceCoverageItemToInsuranceCoverageItemResourceConverter
import com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem.CreateInsuranceCoverageItemUseCase
import com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem.GetAllInsuranceCoverageItemsUseCase
import com.ranefare.plancatalogservice.core.usecases.insurancecoverageitem.GetInsuranceCoverageItemUseCase
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import javax.inject.Inject

@Controller("/insurance/coverage-items")
class InsuranceCoverageItemCrudController @Inject constructor(
    private val createUseCase: CreateInsuranceCoverageItemUseCase,
    private val getUseCase: GetInsuranceCoverageItemUseCase,
    private val getAllUseCase: GetAllInsuranceCoverageItemsUseCase,
    private val resourceConverter: InsuranceCoverageItemToInsuranceCoverageItemResourceConverter,
    private val resourceAssembler: InsuranceCoverageItemResourceToInsuranceCoverageItemAssembler
) : CrudInsuranceCoverageItemContract {

    @Post
    override fun create(@Body resource: InsuranceCoverageItemResource): HttpResponse<InsuranceCoverageItemResource> {
        val created = createUseCase.execute(resourceAssembler.assemble(resource))
        return HttpResponse.created(resourceConverter.convert(created))
    }

    @Get("/{id}")
    override fun get(@PathVariable("id") id: String): HttpResponse<InsuranceCoverageItemResource> {
        return HttpResponse.ok(resourceConverter.convert(getUseCase.execute(id)))
    }

    @Get
    override fun getAll(): HttpResponse<GetAllResponse<InsuranceCoverageItemResource>> {
        return HttpResponse.ok(
            GetAllResponse(
                items = getAllUseCase.execute().map(resourceConverter::convert)
            )
        )
    }
}