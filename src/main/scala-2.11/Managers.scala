import slick.driver.PostgresDriver.api._

class Managers(tag: Tag) extends Table[(Int, String, String)](tag, "managers") {
  def id = column[Int]("id", O.PrimaryKey) // This is the primary key column
  def name = column[String]("name")
  def surname = column[String]("surname")
  def * = (id, name, surname)
}