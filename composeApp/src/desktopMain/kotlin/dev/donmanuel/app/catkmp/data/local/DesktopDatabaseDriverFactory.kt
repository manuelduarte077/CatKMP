package dev.donmanuel.app.catkmp.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import dev.donmanuel.app.catkmp.CatDatabase
import java.io.File

class DesktopDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {

        val dataBaseName = "cat4.db"

        val dbFile = File(dataBaseName)
        val driver = JdbcSqliteDriver("jdbc:sqlite:$dataBaseName")

        if (!dbFile.exists()) {
            CatDatabase.Schema.create(driver)
        }
        return driver
    }
}