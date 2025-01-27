package com.example.uaspam.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uaspam.ui.View.DetailScreen
import com.example.uaspam.ui.View.DetailScreenPemasok
import com.example.uaspam.ui.View.EntrypmskScreen
import com.example.uaspam.ui.View.EntryprdkScreen
import com.example.uaspam.ui.View.HomeScreen
import com.example.uaspam.ui.View.HomeScreenPemasok
import com.example.uaspam.ui.View.UpdateScreen
import com.example.uaspam.ui.View.UpdateScreenPemasok
import com.example.uaspam.ui.ViewKategori.DetailScreenKategori
import com.example.uaspam.ui.ViewKategori.EntryktgrScreen
import com.example.uaspam.ui.ViewKategori.HomeScreenKategori
import com.example.uaspam.ui.ViewKategori.UpdateScreenKategori
import com.example.uaspam.ui.Viewmerk.DetailScreenMerk
import com.example.uaspam.ui.Viewmerk.EntrymrkScreen
import com.example.uaspam.ui.Viewmerk.HomeScreenMerk
import com.example.uaspam.ui.Viewmerk.UpdateScreenMerk

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(
            DestinasiHome.route
        ) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route)},
                onDetailClick = { id_produk ->
                    navController.navigate("${DestinasiDetail.route}/$id_produk")
                },
                onNavigateToPemasok = { navController.navigate(DestinasiHomePemasok.route)
                },
                onNavigateToKategori = { navController.navigate(DestinasiHomeKategori.route)

                }
                , onNavigateToMerk = { navController.navigate(DestinasiHomeMerk.route)
                }

            )
        }
        composable(
            DestinasiEntry.route
        ) {
            EntryprdkScreen(
                navigateBack = {
                    navController.navigate(DestinasiHome.route){
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.ID_produk){
                    type = NavType.StringType
                }
            )
        ) {
            val id_produk = it.arguments?.getString(DestinasiDetail.ID_produk)
            id_produk?.let {
                DetailScreen(
                    navigateBack = {
                        navController.navigate(DestinasiHome.route) {
                            popUpTo(DestinasiHome.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick =  {
                        navController.navigate("${DestinasiUpdate.route}/$id_produk")
                    },
                )
            }
        }

        composable(
            DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.ID_produk) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_produk = it.arguments?.getString(DestinasiUpdate.ID_produk)
            id_produk?.let { id_produk ->
                UpdateScreen(
                    onBack = {navController.popBackStack()},
                    onNavigate = {navController.popBackStack()}
                )
            }
        }






        ///pengelolaan halaman kategori

        composable(
            DestinasiHomeKategori.route
        ) {
            HomeScreenKategori(
                navigateToItemEntry = { navController.navigate(DestinasiEntryKategori.route) },
                onDetailClick = { id_kategori ->
                    navController.navigate("${DestinasiDetailKategori.route}/$id_kategori")
                },
                navigateBack = { navController.navigate(DestinasiHome.route) }
            )
        }

        composable(
            DestinasiEntryKategori.route
        ) {
            EntryktgrScreen(
                navigateBack = {
                    navController.navigate(DestinasiHomeKategori.route){
                        popUpTo(DestinasiHomeKategori.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            DestinasiDetailKategori.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailKategori.ID_kategori){
                    type = NavType.StringType
                }
            )
        ) {
            val id_kategori = it.arguments?.getString(DestinasiDetailKategori.ID_kategori)
            id_kategori?.let {
                DetailScreenKategori(
                    navigateBack = {
                        navController.navigate(DestinasiHomeKategori.route) {
                            popUpTo(DestinasiHomeKategori.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick =  {
                        navController.navigate("${DestinasiUpdateKategori.route}/$id_kategori")
                    },
                )
            }
        }

        composable(
            DestinasiUpdateKategori.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateKategori.ID_kategori) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_kategori = it.arguments?.getString(DestinasiUpdateKategori.ID_kategori)
            id_kategori?.let { id_kategori ->
                UpdateScreenKategori(
                    onBack = {navController.popBackStack()},
                    onNavigate = {navController.popBackStack()}
                )
            }
        }


        ///pengelola halaman pemasok

        composable(
            DestinasiHomePemasok.route
        ) {
            HomeScreenPemasok(
                navigateToItemEntry = { navController.navigate(DestinasiEntryPemasok.route) },
                onDetailClick = { id_pemasok ->
                    navController.navigate("${DestinasiDetailPemasok.route}/$id_pemasok")
                },
                navigateBack = { navController.navigate(DestinasiHome.route) }
            )
        }

        composable(
            DestinasiEntryPemasok.route
        ) {
            EntrypmskScreen(
                navigateBack = {
                    navController.navigate(DestinasiHomePemasok.route){
                        popUpTo(DestinasiHomePemasok.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            DestinasiDetailPemasok.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailPemasok.ID_pemasok){
                    type = NavType.StringType
                }
            )
        ) {
            val id_pemasok = it.arguments?.getString(DestinasiDetailPemasok.ID_pemasok)
            id_pemasok?.let {
                DetailScreenPemasok(
                    navigateBack = {
                        navController.navigate(DestinasiHomePemasok.route) {
                            popUpTo(DestinasiHomePemasok.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick =  {
                        navController.navigate("${DestinasiUpdatePemasok.route}/$id_pemasok")
                    },
                )
            }
        }

        composable(
            DestinasiUpdatePemasok.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdatePemasok.ID_pemasok) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_pemasok = it.arguments?.getString(DestinasiUpdatePemasok.ID_pemasok)
            id_pemasok?.let { id_pemasok ->
                UpdateScreenPemasok(
                    onBack = {navController.popBackStack()},
                    onNavigate = {navController.popBackStack()}
                )
            }
        }

        ///pengelolaan halaman merk

        composable(
            DestinasiHomeMerk.route
        ) {
            HomeScreenMerk(
                navigateToItemEntry = { navController.navigate(DestinasiEntryMerk.route) },
                onDetailClick = { id_merk ->
                    navController.navigate("${DestinasiDetailMerk.route}/$id_merk")
                },
                navigateBack = { navController.navigate(DestinasiHome.route) }
            )
        }

        composable(
            DestinasiEntryMerk.route
        ) {
            EntrymrkScreen(
                navigateBack = {
                    navController.navigate(DestinasiHomeMerk.route){
                        popUpTo(DestinasiHomeMerk.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            DestinasiDetailMerk.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailMerk.ID_merk){
                    type = NavType.StringType
                }
            )
        ) {
            val id_merk = it.arguments?.getString(DestinasiDetailMerk.ID_merk)
            id_merk?.let {
                DetailScreenMerk(
                    navigateBack = {
                        navController.navigate(DestinasiHomeMerk.route) {
                            popUpTo(DestinasiHomeMerk.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick =  {
                        navController.navigate("${DestinasiUpdateMerk.route}/$id_merk")
                    },
                )
            }
        }

        composable(
            DestinasiUpdateMerk.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateMerk.ID_merk) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_merk = it.arguments?.getString(DestinasiUpdateMerk.ID_merk)
            id_merk?.let { id_merk ->
                UpdateScreenMerk(
                    onBack = {navController.popBackStack()},
                    onNavigate = {navController.popBackStack()}
                )
            }
        }
    }
}

