package org.website.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@PropertySource("/applications.properties")

public class WebController {
    // Đón nhận request GET
    @GetMapping("/") // Nếu người dùng request tới địa chỉ "/"
    public String index() {
        return "index"; // Trả về file index.html
    }

    @GetMapping("/hello")
    public String hello(
            // Request param "name" sẽ được gán giá trị vào biến String
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            // Model là một object của Spring Boot, được gắn vào trong mọi request.
            Model model
    ) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        // Tạo Thông tin
        List<Info> profile = new ArrayList<>();
        profile.add(new Info("fullname", "Trần Trọng Trường"));
        profile.add(new Info("facebook", "https://www.facebook.com/"));
        profile.add(new Info("website", "https://google.com.vn"));
        System.out.println("_______________________________________");
        System.out.println(profile);
        // Đưa thông tin vào Model
        model.addAttribute("profile", profile);

        // TRả về template profile.html
        return "profile";
    }

    // Sử dụng tạm List để chứa danh sách công việc
    // Thay cho Database.
    // Chỉ dùng cách này khi DEMO ^^
    List<Todo> todoList = new CopyOnWriteArrayList<>();

    @GetMapping("/listTodo")
    public String indexList(
            Model model,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        // Trả về đối tượng todoList.
        // Nếu người dùng gửi lên param limit thì trả về sublist của todoList
        model.addAttribute("todoList", limit != null ? todoList.subList(0, limit) : todoList);

        // Trả về template "listTodo.html"
        return "listTodo";
    }

    @GetMapping("/addTodo")
    public String addTodo(Model model) {
        // Thêm mới một đối tượng Todo vào model
        model.addAttribute("todo", new Todo());
        // Trả về template addTodo.html
        return "addTodo";
    }
    @PostMapping("/addTodo")
    public String addTodo(@ModelAttribute Todo todo) {
        todoList.add(todo);
        return "success";
    }
}
