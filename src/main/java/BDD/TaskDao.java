package BDD;

import java.util.List;

import rest.Task;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface TaskDao {
	
	
	@SqlUpdate("create table tasks (idt integer primary key autoincrement, namet varchar(20), description varchar(100), state varchar(20))")
	public void createTaskTable();
	
	@SqlUpdate("insert into tasks (namet, description, state)")
	@GetGeneratedKeys
	public int insert(@BindBean int id);
	
	@SqlUpdate("update tasks set namet = :namet, description = :description, state = :state")
	public void update(@BindBean Task t);
	
	@SqlQuery("select count(*) from tasks")
	public int count();
	
	@SqlQuery("select * from tasks where idt = :idt")
    @RegisterMapperFactory(BeanMapperFactory.class)
	public Task findByIdt(@Bind("idt") int idt);
	
	@SqlUpdate("delete from tasks where idt = :idt")
	public int deleteTask(@Bind("idt") int idt);
	
	@SqlUpdate("drop table if exists tasks")
	public void dropClientTask();
	
	@SqlQuery("select * from users order by idt")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Task> all();
	
	

	public void close();
	
}