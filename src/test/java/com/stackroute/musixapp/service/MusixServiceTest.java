package com.stackroute.musixapp.service;

import com.stackroute.musixapp.domain.Music;
import com.stackroute.musixapp.exceptions.MusicAlreadyExistsException;
import com.stackroute.musixapp.exceptions.MusicNotFoundException;
import com.stackroute.musixapp.repository.MusicRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class MusixServiceTest {

    Musix  musix;

    //Create a mock for UserRepository
    @Mock
    MusixRepository musixRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    MusixServiceImpl musixService;
    List<Musix> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        musix = new Musix();
        musix.setId(20);
        musix.setName("parade");
        musix.setRating(5);
        musix.setComments("wow!");
        list = new ArrayList<>();
        list.add(musix);


    }

    @Test
    public void saveMusicTestSuccess() throws MusicAlreadyExistsException {

        when(musicRepository.save((Music) any())).thenReturn(music);
        Music savedTrack = musicService.saveNewMusic(music);
        assertEquals(music,savedMusic);

        //verify here verifies that userRepository save method is only called once
        verify(musicRepository,times(1)).save(music);

    }

    @Test(expected = MusicAlreadyExistsException.class)
    public void saveMusicTestFailure() throws MusicAlreadyExistsException {
        when(musicRepository.save((Music) any())).thenReturn(null);
        Music savedMusic = musicService.saveNewMusic(music);
        System.out.println("savedUser" + savedMusic);
    }

    @Test
    public void testGetAllMusic() throws MusicNotFoundException{

        musicRepository.save(musix);
        //stubbing the mock to return specific data
        when(musicRepository.findAll()).thenReturn(list);
        List<Music> userlist = musicService.getMusic();
        assertEquals(list,userlist);
    }

    @Test
    public void deleteMusicTestSuccess() throws MusidAlreadyExistsException {

        musicRepository.delete(music);
        boolean deletedMusic=musicRepository.existsById(20);
        assertEquals(false,deletedMusic);
    }

    @Test
    public void updateMusicTest() throws MusicNotFoundException
    {
        when(musicRepository.save((Music) any())).thenReturn(music);
        Music updateMusic = null;
        try {
            updateMusic = musicService.saveNewMusic(music);
        } catch (MusicAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertEquals(music,updateMusic);
    }


}
