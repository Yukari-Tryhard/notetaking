package com.cnpmm.notetaking.service;

import com.cnpmm.notetaking.repository.TrashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrashService {

    @Autowired
    private TrashRepository trashRepository;

    public TrashService(TrashRepository trashRepository){
        this.trashRepository = trashRepository;
    }
}
