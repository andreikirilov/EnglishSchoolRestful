package englishschoolrestful;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClassesController {

    @RequestMapping("/index")
    public String mainIndex(Model model) {
        return "index";
    }

    @RequestMapping("/selclass")
    public String selClassForm(@RequestParam(value = "name", required = false, defaultValue = "") String name, Model model) {
        model.addAttribute("selectAll", JavaToMySQL.selAllClass(name));
        return "selclass";
    }

    @RequestMapping(value = "/selname", method = RequestMethod.GET)
    public String selNameForm(Model model) {
        Classes classes = new Classes();
        model.addAttribute("newClass", classes);
        return "selname";
    }

    @RequestMapping(value = "/addstudent", method = RequestMethod.GET)
    public String addStudentForm(Model model) {
        Students student = new Students();
        model.addAttribute("newStudent", student);
        return "addstudent";
    }

    @RequestMapping(value = "/addstudent", method = RequestMethod.POST)
    public String addStudentSubmit(@ModelAttribute Students student, Model model) {
        System.out.println(student.toString());
        model.addAttribute("student", student);
        JavaToMySQL.addStudent(student);
        return "addresult";
    }

    @RequestMapping(value = "/insclass", method = RequestMethod.GET)
    public String insClassForm(Model model) {
        Classes classes = new Classes();
        model.addAttribute("newClass", classes);
        return "insclass";
    }

    @RequestMapping(value = "/insclass", method = RequestMethod.POST)
    public String insClassSubmit(@ModelAttribute Classes classes, Model model) {
        System.out.println(classes.toString());
        model.addAttribute("classes", classes);
        JavaToMySQL.insClass(classes);
        return "insresult";
    }

    @RequestMapping(value = "/updclass", method = RequestMethod.GET)
    public String uptClassForm(Model model) {
        Classes classes = new Classes();
        model.addAttribute("newClass", classes);
        return "updclass";
    }

    @RequestMapping(value = "/updclass", method = RequestMethod.POST)
    public String updClassSubmit(@ModelAttribute Classes classes, Model model) {
        System.out.println(classes.toString());
        model.addAttribute("classes", classes);
        JavaToMySQL.updClass(classes);
        return "updresult";
    }

    @RequestMapping(value = "/delclass", method = RequestMethod.GET)
    public String delClassForm(Model model) {
        Classes classes = new Classes();
        model.addAttribute("newClass", classes);
        return "delclass";
    }

    @RequestMapping(value = "/delclass", method = RequestMethod.POST)
    public String delClassSubmit(@ModelAttribute Classes classes, Model model) {
        System.out.println(classes.toString());
        model.addAttribute("classes", classes);
        JavaToMySQL.delClass(classes);
        return "delresult";
    }

    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.GET, produces = "text/plain")
    public String checkUserPass(@RequestParam(value = "name") String name, @RequestParam(value = "pass") String pass) {
        return JavaToMySQL.checkUserPass(name, pass);
    }

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "text/plain")
    public String selectAllInfo(@RequestParam(value = "name") String name) {
        return JavaToMySQL.selectAllInfo(name).toString();
    }
}