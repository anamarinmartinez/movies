package com.anamarin.movies.model

import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine

interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}

class PlayServicesLocationDataSource(activity: Activity) : LocationDataSource {

    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

    @SuppressLint("MissingPermission")
    override suspend fun findLastLocation(): Location? =
        suspendCancellableCoroutine { continuation ->
            fusedLocationProviderClient.lastLocation
                .addOnCompleteListener {
                    continuation.resume(it.result)
                }
        }
}