import slick.driver.PostgresDriver.api._
import com.typesafe.config.ConfigFactory
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {

  val config = ConfigFactory.load();
  val db = Database.forConfig("pg_conf")

  //define implicits GetResults to be able to map result to a class
  implicit val getManager = GetResult(res => Manager(res.nextInt, res.nextString, res.nextString))

  try {
//    val managers = TableQuery[Managers]
//    val persons = TableQuery[Persons]
//
//    val personNames = persons.withFilter(_.id > 1).map(_.name)
//
//    val setup = DBIO.seq(
//      (persons.schema ++ managers.schema).create,
//
//      persons += (1, "One", "Oneone", 1),
//      persons += (2, "Two", "Twotwo", 1),
//      persons += (3, "Three", "Threethree", 2),
//
//      managers ++= Seq(
//        (1, "Manager", "Managermanager"),
//        (2, "Lord", "Oftherings")
//      )
//    )
//
//    db.run(setup)
//    db.run(personNames.result).map(_.foreach(println))

//    db.run(persons.result).map(_.foreach {
//      case (id, name, surname, manager) =>
//        println("  " + id + "\t" + name + "\t" + surname + "\t" + manager)
//    })

//    db.run(sql"""select * from managers""".as[(Int, String, String)]).map(_.foreach(println))
//    db.run(sql"""select * from managers""".as[Manager]).map(_.foreach(println))

    val personToManager = PersonDAO.listPersonWithManager.result
    val res = db.run(personToManager)
    res.onSuccess{case s => s.foreach(println)}

    db.run(ManagerDAO.findById(1).result).map(_.foreach(println))

  } finally db.close
}



