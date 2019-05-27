package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.Note;
import com.jiefeng.ssm.beanExtend.UserExtend;
import com.jiefeng.ssm.beanExtend.UserOrAdminType;
import com.jiefeng.ssm.dao.NoteDao;
import com.jiefeng.ssm.service.NoteService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("NoteService")
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;

    @Override
    public boolean addNote(Note note) {
        System.out.println(note);
        return noteDao.addNote(note);
    }

    @Override
    public Note getNoteByPrimaryKey(Integer noteId) {
        return noteDao.getNoteByPrimaryKey(noteId);
    }

    @Override
    public List<Note> getAllNoteByUserId() {

        UserOrAdminType userOrAdminType = (UserOrAdminType) SecurityUtils.getSubject().getPrincipal();
        UserExtend userExtend = (UserExtend) userOrAdminType.getObject();
        Integer userId = userExtend.getId();
        return noteDao.getAllNoteByUserId(userId);

    }

    @Override
    public boolean updateNote(Note note) {
        return noteDao.updateNote(note);
    }

    @Override
    public List<Note> getAllNote() {
        return noteDao.getAllNote();
    }

    @Override
    public List<Note> getHotNote() {
        return noteDao.getHotNote();
    }
}
