package `in`.khofid.schedule.search

import `in`.khofid.schedule.model.Match

data class SearchMatchResponse(
    val event: List<Match>
)