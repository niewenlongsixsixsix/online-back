package com.jiefeng.ssm.dao;

import com.jiefeng.ssm.bean.Note;

import java.util.List;

public interface NoteDao {

    boolean addNote(Note note);

    Note getNoteByPrimaryKey(Integer noteId);

    List<Note> getAllNoteByUserId(Integer userId);

    boolean updateNote(Note note);
}
