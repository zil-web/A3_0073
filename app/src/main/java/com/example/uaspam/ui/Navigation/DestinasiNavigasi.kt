package com.example.uaspam.ui.Navigation

//pemasok
interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}


object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail-produk"
    override val titleRes = "DetailProduk"
    const val ID_produk = "idproduk"
    val routesWithArg = "$route/{$ID_produk}"
}

object DestinasiHome : DestinasiNavigasi {
    override val route = "homeproduk"
    override val titleRes = "HomeProduk"
}

object DestinasiEntry : DestinasiNavigasi {
    override val route = "itementry"
    override val titleRes = "EntryProduk"
}

object DestinasiUpdate : DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "UpdateProduk"
    const val ID_produk = "idproduk"
    val routesWithArg = "$route/{$ID_produk}"
}

// kategori



object DestinasiDetailKategori: DestinasiNavigasi {
    override val route = "detail_kategori"
    override val titleRes = "Detailkategori"
    const val ID_kategori = "idkategori"
    val routesWithArg = "$route/{$ID_kategori}"
}

object DestinasiHomeKategori: DestinasiNavigasi {
    override val route = "homeKategori"
    override val titleRes= "HomeKategori"
}

object DestinasiEntryKategori: DestinasiNavigasi{
    override val route = "item_entryktgr"
    override val titleRes= "Entryktgr"
}

object DestinasiUpdateKategori: DestinasiNavigasi{
    override val route = "updatektgr"
    override val titleRes = "Updatektgr"
    const val ID_kategori = "idkategori"
    val routesWithArg = "$route/{$ID_kategori}"
}


//pemasok
object DestinasiDetailPemasok: DestinasiNavigasi {
    override val route = "detail_pemasok"
    override val titleRes = "DetailPmsk"
    const val ID_pemasok = "idpemasok"
    val routesWithArg = "$route/{$ID_pemasok}"
}

object DestinasiHomePemasok: DestinasiNavigasi {
    override val route = "homePemasok"
    override val titleRes= "HomePemasok"
}

object DestinasiEntryPemasok: DestinasiNavigasi{
    override val route = "item_entrypmsk"
    override val titleRes= "Entrypmsk"
}

object DestinasiUpdatePemasok: DestinasiNavigasi{
    override val route = "updatepmsk"
    override val titleRes = "Updatepmsk"
    const val ID_pemasok = "idpemasok"
    val routesWithArg = "$route/{$ID_pemasok}"
}

//merk
object DestinasiDetailMerk: DestinasiNavigasi {
    override val route = "detail_merk"
    override val titleRes = "DetailMerk"
    const val ID_merk = "idmerk"
    val routesWithArg = "$route/{$ID_merk}"
}

object DestinasiHomeMerk: DestinasiNavigasi {
    override val route = "homeMerk"
    override val titleRes= "HomeMerk"
}

object DestinasiEntryMerk: DestinasiNavigasi{
    override val route = "item_entrymerk"
    override val titleRes= "Entrymerk"
}

object DestinasiUpdateMerk: DestinasiNavigasi{
    override val route = "updatemerk"
    override val titleRes = "Updatemerk"
    const val ID_merk = "idmerk"
    val routesWithArg = "$route/{$ID_merk}"

}