package com.stackroute.musixapp.service;

import com.stackroute.musixapp.domain.Musix;
import com.stackroute.musixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.musixapp.exceptions.TrackNotFoundException;
import com.stackroute.musixapp.repository.MusixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusixServiceImpl implements MusixService {

    MusixRepository musixRepository;

    @Autowired
    public MusixServiceImpl(MusixRepository musixRepository) {
       this.musixRepository = musixRepository;
    }

    @Override
    public Musix saveNewMusix(Musix musix) throws TrackAlreadyExistsException {
        if(musixRepository.existsById(musix.getId())){
            throw new TrackAlreadyExistsException("User already exists!");
        }
        Musix savedTrack = musixRepository.save(musix);
        if(savedTrack == null) {
            throw new TrackAlreadyExistsException("User already exists!");
        }
        return savedTrack;
    }

    @Override
    public List<Musix> getMusix() {
        return musixRepository.findAll();
    }

    @Override
    public Musix getById(int id) throws TrackNotFoundException{
        Optional<Musix> userId = musixRepository.findById(id);
        if(userId.isEmpty()){
            throw new TrackNotFoundException("Track not found!");
        }
        return userId.get();
    }

    @Override
    public void deleteById(int id) throws TrackNotFoundException {
        Optional<Musix> userId = musixRepository.findById(id);
        if(userId.isEmpty()){
            throw new TrackNotFoundException("Track not found!");
        }
        musixRepository.deleteById(id);
    }

    @Override
    public boolean updateById(Musix musix, int id) throws TrackNotFoundException {
        Optional<Musix> userOptional = musixRepository.findById(id);

        if(userOptional.isEmpty()){
            throw new TrackNotFoundException("Track not found!");
        }

        musix.setId(id);

        musixRepository.save(musix);
        return true;
    }

    @Override
    public List<Musix> getByName(String name) {
        List<Musix> userId = musixRepository.findTitleByName(name);
        return userId;
    }

}
