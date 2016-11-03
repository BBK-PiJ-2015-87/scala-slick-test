import slick.driver.PostgresDriver.api._
import com.typesafe.config.ConfigFactory
import scala.concurrent.ExecutionContext.Implicits.global

case class Person(id: Int, name: String, surname: String, manager: Manager)

case class Manager(id: Int, name: String, surname: String)

object Main extends App {

  class Managers(tag: Tag) extends Table[(Int, String, String)](tag, "managers") {
    def id = column[Int]("id", O.PrimaryKey) // This is the primary key column
    def name = column[String]("name")
    def surname = column[String]("surname")
    // Every table needs a * projection with the same type as the table's type parameter
    def * = (id, name, surname)
  }


  class Persons(tag: Tag) extends Table[(Int, String, String, Int)](tag, "persons") {
    def id = column[Int]("id", O.PrimaryKey)
    def name = column[String]("name")
    def surname = column[String]("surname")
    def manager = column[Int]("manager")
    def * = (id, name, surname, manager)

//    // A reified foreign key relation that can be navigated to create a join
//    def supplier = foreignKey("manager_FK", manager, managers)(_.id)
  }




  val config = ConfigFactory.load();
  val db = Database.forConfig("pg_conf")

  try {
    val managers = TableQuery[Managers]
    val persons = TableQuery[Persons]

    val setup = DBIO.seq(
      (persons.schema ++ managers.schema).create,

      persons += (1, "One", "Oneone", 1),
      persons += (2, "Two", "Twotwo", 1),
      persons += (3, "Three", "Threethree", 1),

      managers ++= Seq(
        (1, "Manager", "Managermanager"),
        (2, "Lord", "Oftherings")
      )
    )

    db.run(setup)

    db.run(persons.result).map(_.foreach {
      case (id, name, surname, manager) =>
        println("  " + id + "\t" + name + "\t" + surname + "\t" + manager)
    })

  } finally db.close

}



