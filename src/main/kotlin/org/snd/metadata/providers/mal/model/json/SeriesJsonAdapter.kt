package org.snd.metadata.providers.mal.model.json

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.snd.metadata.providers.mal.model.AlternativeTitles
import org.snd.metadata.providers.mal.model.Author
import org.snd.metadata.providers.mal.model.Picture
import org.snd.metadata.providers.mal.model.Serialization
import org.snd.metadata.providers.mal.model.Series
import org.snd.metadata.providers.mal.model.Series.NSFW

class SeriesJsonAdapter {
    @FromJson
    fun fromJson(json: SeriesJson): Series {
        return Series(
            id = json.id,
            title = json.title,
            alternativeTitles = json.alternative_titles?.let { altTitlesFromJson(it) },
            mainPicture = json.main_picture?.let { pictureFromJson(it) },
            synopsis = json.synopsis,
            status = Series.Status.valueOf(json.status.uppercase()),
            genres = json.genres.mapTo(HashSet()) { it.name },
            authors = json.authors.map { authorFromJson(it) },
            serialization = json.serialization.map { serializationFromJson(it) },
            pictures = json.pictures.map { pictureFromJson(it) },
            background = json.background,
            nsfw = json.nsfw?.let { NSFW.valueOf(it.uppercase()) },
            startDate = json.start_date,
            endDate = json.end_date,
            mediaType = Series.MediaType.valueOf(json.media_type.uppercase()),
            numVolumes = json.num_volumes,
            numChapters = json.num_chapters,
            mean = json.mean,
            rank = json.rank,
            popularity = json.popularity,
            numListUsers = json.num_list_users,
            numScoringUsers = json.num_scoring_users,
            createdAt = json.created_at,
            updatedAt = json.updated_at
        )
    }

    @ToJson
    fun toJson(@Suppress("UNUSED_PARAMETER") series: Series): SeriesJson {
        throw UnsupportedOperationException()
    }

    private fun altTitlesFromJson(json: AlternativeTitlesJson): AlternativeTitles {
        return AlternativeTitles(
            synonyms = json.synonyms,
            en = json.en,
            ja = json.ja
        )
    }

    private fun pictureFromJson(json: PictureJson): Picture {
        return Picture(
            large = json.large,
            medium = json.medium
        )
    }

    private fun authorFromJson(json: AuthorJson): Author {
        return Author(
            id = json.node.id,
            firstName = json.node.first_name,
            lastName = json.node.last_name,
            role = json.role
        )
    }

    private fun serializationFromJson(json: SerializationJson): Serialization {
        return Serialization(
            id = json.node.id,
            name = json.node.name
        )
    }
}
