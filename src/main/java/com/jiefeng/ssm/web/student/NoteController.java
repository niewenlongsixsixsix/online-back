package com.jiefeng.ssm.web.student;

import com.jiefeng.ssm.bean.Note;
import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.beanExtend.UserExtend;
import com.jiefeng.ssm.beanExtend.UserOrAdminType;
import com.jiefeng.ssm.service.NoteService;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("note")
public class NoteController {

    Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    @RequestMapping(value = "addNote",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addNote(@RequestBody Map map){
        Map<String,Object> modelMap = new HashMap<>();

        JSONObject noteInfo = JSONObject.fromObject(map.get("noteInfo"));

        Note note = (Note) JSONObject.toBean(noteInfo, Note.class);

        logger.info(note.toString());

        UserOrAdminType userOrAdminType = (UserOrAdminType) SecurityUtils.getSubject().getPrincipal();
        UserExtend userExtend = (UserExtend) userOrAdminType.getObject();
        Integer userId = userExtend.getId();

        note.setCreateBy(new User(userId));
        note.setCreateTime(new Date());
        note.setHighQuality(0);

        boolean b = noteService.addNote(note);
        if(b)
            modelMap.put("success",true);
        else
            modelMap.put("success",false);

        return modelMap;
    }


    @RequestMapping(value = "updateNote",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateNote(@RequestBody Map map){
        Map<String,Object> modelMap = new HashMap<>();

        JSONObject noteInfo = JSONObject.fromObject(map.get("noteInfo"));

        logger.info("noteInfo: " + noteInfo);
        Note note = (Note) JSONObject.toBean(noteInfo, Note.class);

        logger.info(note.toString());

        note.setUpdateTime(new Date());
        boolean b = noteService.updateNote(note);
        if(b)
            modelMap.put("success",true);
        else
            modelMap.put("success",false);

        return modelMap;
    }

    @RequestMapping(value = "getNoteByUserId",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getNoteByUserId(){
        Map<String,Object> modelMap = new HashMap<>();

        List<Note> allNoteByUserId = noteService.getAllNoteByUserId();
        if(allNoteByUserId == null){
            modelMap.put("success",false);
        }else{
            modelMap.put("success",true);
            modelMap.put("noteList",allNoteByUserId);
        }
        return modelMap;
    }

    @RequestMapping(value = "getNoteByPrimaryKey/{noteId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getNoteByPrimaryKey(@PathVariable Integer noteId){
        Map<String,Object> modelMap = new HashMap<>();
        Note noteByPrimaryKey = noteService.getNoteByPrimaryKey(noteId);
        if(noteByPrimaryKey == null){
            modelMap.put("success",false);
        }else{
            modelMap.put("success",true);
            modelMap.put("noteList",noteByPrimaryKey);
        }
        return modelMap;
    }


}
