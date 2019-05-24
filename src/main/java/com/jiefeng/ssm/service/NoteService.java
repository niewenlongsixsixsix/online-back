package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.Note;

import java.util.List;

public interface NoteService {

    boolean addNote(Note note);

    Note getNoteByPrimaryKey(Integer noteId);

    List<Note> getAllNoteByUserId();

    boolean updateNote(Note note);
}
