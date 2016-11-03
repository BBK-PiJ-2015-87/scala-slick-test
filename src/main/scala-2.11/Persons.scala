import slick.driver.PostgresDriver.api._


class Persons(tag: Tag) extends Table[(Int, String, String, Int)](tag, "persons") {
  def id = column[Int]("id", O.PrimaryKey)
  def name = column[String]("name")
  def surname = column[String]("surname")
  def manager = column[Int]("manager")
  def * = (id, name, surname, manager)
}