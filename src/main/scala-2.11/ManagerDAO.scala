import slick.lifted.TableQuery
import slick.driver.PostgresDriver.api._

object ManagerDAO extends TableQuery(new Managers(_)){
  val findById = this.findBy(_.id)
}
