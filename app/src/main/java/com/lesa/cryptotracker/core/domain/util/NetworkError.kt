package com.lesa.cryptotracker.core.domain.util

enum class NetworkError : Error {
    NO_INTERNET_CONNECTION,
    REQUEST_TIMEOUT,
    SERIALIZATION_ERROR,
    SERVER_ERROR,
    TOO_MANY_REQUESTS,
    UNKNOWN_ERROR,
}
