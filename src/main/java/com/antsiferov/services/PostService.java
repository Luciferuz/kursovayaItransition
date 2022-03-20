package com.antsiferov.services;

import com.antsiferov.Constants;
import com.antsiferov.entity.Post;
import com.antsiferov.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public String getURLs(MultipartFile[] pictures){
        return pictures[0].isEmpty() ? Constants.defaultImg : separateURLs(correctURLs(pictures));
    }

    private ArrayList<String> correctURLs(MultipartFile[] pictures) {
        ArrayList<String> URLs = new ArrayList<>();
        for (MultipartFile picture : pictures) {
            String pictureName = picture.getOriginalFilename();
            String mainURLPart = Constants.mainURLPart;
            String URL = mainURLPart + pictureName.replace(" ", "+");
            URLs.add(URL);
        }
        return URLs;
    }

    private String separateURLs(ArrayList<String> URLs) {
        StringBuilder separatedFormatURL = new StringBuilder();
        for (String url : URLs) {
            separatedFormatURL.append(url);
            separatedFormatURL.append(" ");
        }
        return separatedFormatURL.toString();
    }

    public List<Post> search(String text) {
        return postRepository.search(text);
    }
}
