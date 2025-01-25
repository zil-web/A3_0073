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
    override val titleRes = "Detailktgr"
    const val ID_kategori = "idkategori"
    val routesWithArg = "$route/{$ID_kategori}"
}

object DestinasiHomeKategori: DestinasiNavigasi {
    override val route = "homektgr"
    override val titleRes= "Homektgr"
}

object DestinasiEntryKategori: DestinasiNavigasi{
    override val route = "item_entryktgr"
    override val titleRes= "Entryktgr"
}

object DestinasiUpdatektgr: DestinasiNavigasi{
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
    override val route = "homepmsk"
    override val titleRes= "Homepmsk"
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