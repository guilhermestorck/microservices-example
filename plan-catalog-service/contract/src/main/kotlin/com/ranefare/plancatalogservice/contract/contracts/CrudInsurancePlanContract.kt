package com.ranefare.plancatalogservice.contract.contracts

import com.ranefare.plancatalogservice.contract.domains.resources.InsurancePlanResource
import io.micronaut.http.HttpResponse

interface CrudInsurancePlanContract : CrudContract<InsurancePlanResource, String> {

    fun addCoverageItem(planId: String, coverageItemId: String): HttpResponse<InsurancePlanResource>

    fun removeCoverageItem(planId: String, coverageItemId: String): HttpResponse<InsurancePlanResource>
}
