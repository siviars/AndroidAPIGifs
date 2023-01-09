package com.gifimages;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;


import java.util.List;

import static org.junit.Assert.assertEquals;


public class MainViewModelTest {

    public MainViewModel mainViewModel;
    @Spy
    private MainRepository mainRepositorySpy;
    public List<String> gifList;

    private String apiKey = "h18h8Fk5bL7NPhHZwzKTQvjIZl68yHPO";
    private String limit = "50";


    @Test
    public void testGifLoad() {


        fillList();
        Mockito.when(mainRepositorySpy.searchGifs(apiKey, limit, "cat")).
                thenReturn(new MutableLiveData<>(gifList));
//
        mainViewModel.searchGifs("cat");
        assertEquals(12, mainViewModel.getVolumesResponseLiveData().getValue().size());

    }

    private void fillList() {
        gifList.add("https://media4.giphy.com/media/3o6Zt481isNVuQI1l6/giphy.gif" +
                "?cid=680c57e6xp90j0ttz6lz3zzzdgxqtg6r8kyvemntxt1jag11&rid=giphy.gif&ct=g");
        gifList.add("https://media4.giphy.com/media/3oriO0OEd9QIDdllqo/giphy.gif" +
                "?cid=680c57e6xp90j0ttz6lz3zzzdgxqtg6r8kyvemntxt1jag11&rid=giphy.gif&ct=g");
        gifList.add("https://media0.giphy.com/media/BzyTuYCmvSORqs1ABM/giphy.gif" +
                "?cid=680c57e6xp90j0ttz6lz3zzzdgxqtg6r8kyvemntxt1jag11&rid=giphy.gif&ct=g");
        gifList.add("https://media1.giphy.com/media/ICOgUNjpvO0PC/giphy.gif" +
                "?cid=680c57e6xp90j0ttz6lz3zzzdgxqtg6r8kyvemntxt1jag11&rid=giphy.gif&ct=g");
        gifList.add("https://media3.giphy.com/media/MDJ9IbxxvDUQM/giphy.gif" +
                "?cid=680c57e6xp90j0ttz6lz3zzzdgxqtg6r8kyvemntxt1jag11&rid=giphy.gif&ct=g");
        gifList.add("https://media0.giphy.com/media/mlvseq9yvZhba/giphy.gif" +
                "?cid=680c57e6xp90j0ttz6lz3zzzdgxqtg6r8kyvemntxt1jag11&rid=giphy.gif&ct=g");
        gifList.add("https://media4.giphy.com/media/l0ExdMHUDKteztyfe/giphy.gif" +
                "?cid=680c57e6xp90j0ttz6lz3zzzdgxqtg6r8kyvemntxt1jag11&rid=giphy.gif&ct=g");
        gifList.add("https://media4.giphy.com/media/jpbnoe3UIa8TU8LM13/giphy.gif" +
                "?cid=680c57e6xp90j0ttz6lz3zzzdgxqtg6r8kyvemntxt1jag11&rid=giphy.gif&ct=g");
        gifList.add("https://media1.giphy.com/media/3oEduQAsYcJKQH2XsI/giphy.gif" +
                "?cid=680c57e6xp90j0ttz6lz3zzzdgxqtg6r8kyvemntxt1jag11&rid=giphy.gif&ct=g");
        gifList.add("https://media2.giphy.com/media/8vQSQ3cNXuDGo/giphy.gif" +
                "?cid=680c57e6xp90j0ttz6lz3zzzdgxqtg6r8kyvemntxt1jag11&rid=giphy.gif&ct=g");
        gifList.add("https://media1.giphy.com/media/yFQ0ywscgobJK/giphy.gif" +
                "?cid=680c57e6xp90j0ttz6lz3zzzdgxqtg6r8kyvemntxt1jag11&rid=giphy.gif&ct=g");
    }

}