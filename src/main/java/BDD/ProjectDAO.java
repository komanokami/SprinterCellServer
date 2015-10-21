package BDD;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import rest.Project;

public interface ProjectDAO {

	@SqlUpdate("create table projects (idp integer primary key autoincrement, namep varchar(20), descriptionp varchar(100), tasksp text, membersp text)")
	public void createProjectTable();
	
	@SqlUpdate("insert into tasks (namep, descriptionp, tasksp, membersp)")
	@GetGeneratedKeys
	public int insert(@BindBean Project p);
	
	@SqlUpdate("update projects set namep = :namep, descriptionp = :descriptionp, tasksp = :tasksp, membersp = :membersp")
	public void update(@BindBean Project p);
	
	@SqlQuery("select count(*) from projects")
	public int countP();
	
	@SqlQuery("select * from projects where idp = :idp")
    @RegisterMapperFactory(BeanMapperFactory.class)
	public Project findByIdp(@Bind("idp") int idp);
	
	@SqlUpdate("delete from projects where idp = :idp")
	public int deleteProject(@Bind("idp") int idp);
	
	@SqlUpdate("drop table if exists projects")
	public void dropProject();

	public void close();
}
