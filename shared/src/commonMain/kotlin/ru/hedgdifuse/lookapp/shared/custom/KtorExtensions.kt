package ru.hedgdifuse.lookapp.shared.custom

import ru.hedgdifuse.lookapp.shared.Constants.API.START_URL

fun withStartUrl(endpoint: String) =
    START_URL + endpoint
