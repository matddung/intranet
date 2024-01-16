package com.intranet.service;

import com.intranet.dto.Note.request.NoteSendRequest;
import com.intranet.dto.Note.response.NoteDeleteResponse;
import com.intranet.dto.Note.response.NoteInfoResponse;
import com.intranet.dto.Note.response.NoteSendResponse;
import com.intranet.entity.Member;
import com.intranet.entity.Note;
import com.intranet.repository.MemberRepository;
import com.intranet.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public NoteSendResponse sendNote(UUID senderAccount, NoteSendRequest request) {
        Member addressee = memberRepository.findByAccount(request.addresseeAccount())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
        Member sender = memberRepository.findById(senderAccount)
                .orElseThrow(() -> new NoSuchElementException("로그인한 회원을 찾을 수 없습니다."));
        Note note = noteRepository.save(Note.from(request, addressee, sender));
        return NoteSendResponse.from(note);
    }

    @Transactional(readOnly = true)
    public List<NoteInfoResponse> getSendNotes(UUID id) {
        return noteRepository.findAllBySenderId(id).stream()
                .map(NoteInfoResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<NoteInfoResponse> getReceiveNotes(UUID id) {
        return noteRepository.findAllByAddresseeId(id).stream()
                .map(NoteInfoResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public NoteInfoResponse readNoteDetail(UUID id) {
        return noteRepository.findById(id)
                .map(note -> {
                    note.setRead(true);
                    note.setReadDate(LocalDateTime.now());
                    noteRepository.save(note);
                    return NoteInfoResponse.from(note);
                })
                .orElseThrow(() -> new NoSuchElementException("쪽지를 찾을 수 없습니다."));
    }

    @Transactional
    public NoteDeleteResponse deleteNote(UUID id) {
        if(!noteRepository.existsById(id)) return new NoteDeleteResponse(false);
        noteRepository.deleteById(id);
        return new NoteDeleteResponse(true);
    }
}