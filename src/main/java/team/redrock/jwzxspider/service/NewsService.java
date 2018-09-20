package team.redrock.jwzxspider.service;

import team.redrock.jwzxspider.utils.response.NewsContentResponse;
import team.redrock.jwzxspider.utils.response.NewslistResponse;

public interface NewsService {
    public NewslistResponse getListFromIp(int page);

    public NewslistResponse getListFromDB(int page);

    public NewsContentResponse getContentFromIp(Long id);

    public NewsContentResponse getContentFromDb(Long id);

    public void deleteNewslist();

}
