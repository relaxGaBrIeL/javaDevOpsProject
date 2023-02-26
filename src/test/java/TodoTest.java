//import com.devops.lbnum_project.Controllers.todo.Task;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TodoTest {
//
//    private Todo todo;
//
//    @Before
//    public void setUp() {
//        todo = new Todo();
//    }
//
//    @Test
//    public void testAddTask() {
//        todo.addTask("Buy groceries");
//        assertEquals(1, todo.getNumTasks());
//    }
//
//    @Test
//    public void testDeleteTask() {
//        todo.addTask("Buy groceries");
//        todo.deleteTask(0);
//        assertEquals(0, todo.getNumTasks());
//    }
//
//    @Test
//    public void testMarkCompleted() {
//        todo.addTask("Buy groceries");
//        todo.markCompleted(0);
//        assertTrue(todo.getTask(0).isCompleted());
//    }
//
//    @Test
//    public void testGetTask() {
//        todo.addTask("Buy groceries");
//        Task task = todo.getTask(0);
//        assertEquals("Buy groceries", task.getName());
//    }
//
//    @Test
//    public void testGetNumTasks() {
//        todo.addTask("Buy groceries");
//        todo.addTask("Do laundry");
//        assertEquals(2, todo.getNumTasks());
//    }
//}