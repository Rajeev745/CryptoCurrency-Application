package com.example.cryptocurrency.data.remote.dto

import com.example.cryptocurrency.domain.model.CoinDetail
import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
    val description: String,
    val development_status: String,
    @SerializedName("first_data_at") val firstDateAt: String,
    @SerializedName("hardware_wallet") val hardwareWallet: Boolean,
    @SerializedName("hash_algorithm") val hashAlgorithm: String,
    val id: String,
    @SerializedName("is_active") val isActive: Boolean,
    @SerializedName("is_new") val isNew: Boolean,
    @SerializedName("last_data_at") val lastDateAt: String,
    val links: Links,
    @SerializedName("links_extended") val linksExtended: List<LinksExtended>,
    val logo: String,
    val message: String,
    val name: String,
    @SerializedName("open_source") val openSource: Boolean,
    @SerializedName("org_structure") val orgStructure: String,
    @SerializedName("proof_type") val proofType: String,
    val rank: Int,
    @SerializedName("started_at") val startedAt: String,
    val symbol: String,
    val tags: List<Tag>,
    val team: List<TeamMember>,
    val type: String,
    val whitepaper: Whitepaper
)

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coinId = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        tags = tags.map { it.name },
        team = team
    )
}