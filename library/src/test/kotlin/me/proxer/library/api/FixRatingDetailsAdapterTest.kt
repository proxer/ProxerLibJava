package me.proxer.library.api

import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import me.proxer.library.entity.info.RatingDetails
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * @author Ruben Gees
 */
class FixRatingDetailsAdapterTest {

    private val adapter = FixRatingDetailsAdapter()
    private val jsonReader = mock(JsonReader::class.java)
    private val delegate = Moshi.Builder().build().adapter(RatingDetails::class.java)

    @Test
    fun testFromJson() {
        `when`(jsonReader.nextString()).then {
            "{\"genre\":\"5\",\"story\":\"5\",\"animation\":\"5\"," + "\"characters\":\"5\",\"music\":\"5\"}"
        }

        assertThat(adapter.fromJson(jsonReader, delegate)).isEqualTo(RatingDetails(5, 5, 5, 5, 5))
    }

//    @Test
//    fun testFromJsonPartial() {
//        assertThat(adapter.fromJson("{\"genre\":\"5\",\"story\":\"5\",\"animation\":\"5\"}"))
//            .isEqualTo(RatingDetails(5, 5, 5, 0, 0))
//    }
//
//    @Test
//    fun testFromJsonWeirdArray() {
//        assertThat(adapter.fromJson("[]"))
//            .isEqualTo(RatingDetails(0, 0, 0, 0, 0))
//    }
//
//    @Test
//    fun testFromJsonEmpty() {
//        assertThat(adapter.fromJson(""))
//            .isEqualTo(RatingDetails(0, 0, 0, 0, 0))
//    }
//
//    @Test
//    fun testFromJsonInvalid() {
//        assertThat(adapter.fromJson("{\"invalid\":\"invalid\"}"))
//            .isEqualTo(RatingDetails(0, 0, 0, 0, 0))
//    }
}
