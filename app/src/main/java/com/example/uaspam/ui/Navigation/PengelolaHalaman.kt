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
import com.example.uaspam.ui.View.EntryprdkScreen
import com.example.uaspam.ui.View.HomeScreen
import com.example.uaspam.ui.View.HomeScreenPemasok
import com.example.uaspam.ui.View.UpdateScreen

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
                onNavigateToKategori = {

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

        composable(
            DestinasiEntry.route
        ) {
            EntryprdkScreen(
                navigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            DestinasiHomePemasok.route
        ) {
            HomeScreenPemasok(
                navigateToItemEntry = { navController.navigate(DestinasiEntryPemasok.route) },
                onDetailClick = { id_pemasok ->
                    navController.navigate("${DestinasiDetailPemasok.route}/$id_pemasok")
                }
            )
        }


    }

}

