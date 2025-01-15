package com.example.applenotesclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.applenotesclone.ui.add_edit_note.AddEditNoteScreen
import com.example.applenotesclone.ui.folder_list.FolderListScreen
import com.example.applenotesclone.ui.note_list.NoteListScreen
import com.example.applenotesclone.ui.theme.AppleNotesCloneTheme
import com.example.applenotesclone.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppleNotesCloneTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.FOLDER_LIST) {
                    composable(Routes.FOLDER_LIST) {
                        FolderListScreen(onNavigate = {
                            navController.navigate(it.route)
                        })
                    }
                    composable(route = Routes.NOTE_LIST + "?folderId={folderId}",
                        arguments = listOf(navArgument(name = "folderId") {
                            type = NavType.IntType
                            defaultValue = -1
                        })
                    ) {
                        NoteListScreen(
                            onPopBackStack = {
                            navController.popBackStack()
                        }, onNavigate = {
                            navController.navigate(it.route)
                        })
                    }
                    composable(route = Routes.ADD_EDIT_NOTE + "?folderId={folderId}&noteId={noteId}",
                        arguments = listOf(
                            navArgument(name = "folderId") {
                            type = NavType.IntType
                            defaultValue = -1
                        },
                            navArgument(name = "noteId") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        AddEditNoteScreen(
                            onPopBackStack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppleNotesCloneTheme {
        Greeting("Android")
    }
}