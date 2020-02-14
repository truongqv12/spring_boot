package org.website.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
}
