//package com.example.projektzaliczeniowy
//
//import androidx.room.Room
//import androidx.test.InstrumentationRegistry.getContext
//import androidx.test.espresso.matcher.ViewMatchers.assertThat
//import androidx.test.platform.app.InstrumentationRegistry
//import org.hamcrest.CoreMatchers.equalTo
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import java.io.IOException
//import java.security.AccessController.getContext
//class EnityReadWriteTest {
//    private lateinit var userDao: UserDao
//    private lateinit var db: AppDatabase
//
//    @Before
//    fun createDb() {
//        val context = InstrumentationRegistry.getContext()
//        db = Room.inMemoryDatabaseBuilder(
//                context, AppDatabase::class.java).build()
//        userDao = db.userDao()
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun writeUserAndReadInList() {
//        val todo: TodoEntry = TodoEntry("title", "detail")
//        userDao.insertAll(todo)
//        val todoItem = userDao.findByTitle(todo.title)
//        assertThat(todoItem, equalTo(todo))
//    }
//}