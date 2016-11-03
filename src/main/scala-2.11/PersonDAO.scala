import slick.driver.PostgresDriver.api._

object PersonDAO {
  val persons = TableQuery[Persons]
  val managers = TableQuery[Managers]

  def listPersonWithManager = {
    for {
      p <- persons
      m <- managers if p.manager === m.id
    } yield ((p.id, p.name, p.surname, m.id) -> (m.id, m.name, m.surname))
  }

  //  def personManager(): List[(Person, Manager)] = {
  //    for {
  //      p <- persons
  //      m <- managers if p.manager === m.id
  //
  //
  //
  //    } yield new Manager(m.id, m.name, m.surname)
  //  }
}
