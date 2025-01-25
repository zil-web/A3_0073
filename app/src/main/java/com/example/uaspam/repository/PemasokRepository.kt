import com.example.uaspam.model.Pemasok
import com.example.uaspam.model.pemasokResponse
import com.example.uaspam.model.pemasokResponseDetail
import com.example.uaspam.model.produkResponseDetail
import com.example.uaspam.service_api.PemasokService
import java.io.IOException

interface PemasokRepository {
    suspend fun getpemasok(): pemasokResponse
    suspend fun insertpemasok(pemasok: Pemasok)
    suspend fun updatepemasok(id_pemasok: String, pemasok: Pemasok)
    suspend fun deletepemasok(id_pemasok: String)
    suspend fun getpemasokByid_pemasok(id_pemasok: String): pemasokResponseDetail
}

class NetworkpemasokRepository(
    private val pemasokApiService: PemasokService
) : PemasokRepository {

    override suspend fun insertpemasok(pemasok: Pemasok) {
        pemasokApiService.insertpemasok(pemasok)
    }

    override suspend fun updatepemasok(id_pemasok: String, pemasok: Pemasok) {
        pemasokApiService.updatepemasok(id_pemasok, pemasok)
    }

    override suspend fun deletepemasok(id_pemasok: String) {
        try {
            val response = pemasokApiService.deletepemasok(id_pemasok)
            if (!response.isSuccessful) {
                throw IOException(
                    "Failed to delete pemasok. HTTP Status Code: " +
                            "${response.code()}"
                )
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getpemasok(): pemasokResponse {
        return pemasokApiService.getpemasok()
    }

    override suspend fun getpemasokByid_pemasok(id_pemasok: String): pemasokResponseDetail {
        return pemasokApiService.getpemasokByid_pemasok(id_pemasok)
    }
}