package work.mj.com.mj.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;//问题信息
    private boolean showPrevious;//上一页
    private boolean showFirstPage;//第一页
    private boolean showNext;//下一页
    private boolean showEndPage;//末尾页
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;//当前页

    /**
     * 设置分页
     * @param totalPage  当前页数
     * @param page   总页数
     */
    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;

        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }

            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        // 是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        // 是否展示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        // 是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        // 是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }

    }
}