package com.hq2808.blog.base.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.mysql.cj.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTableRequest {
	/** The current page. */
	Integer currentPage;

	/** The per page. */
	Integer perPage;

	/** The filter. */
	String filter;

	/** The sort by. */
	String sortBy;

	/** The sort desc. */
	Boolean sortDesc;
	
	public PageRequest toPageable() {
		currentPage = currentPage != null && currentPage > 0 ? currentPage : 1;
		perPage = perPage != null && perPage > 0 ? perPage : 10;

		PageRequest pageable = null;
		if (StringUtils.isNullOrEmpty(this.getSortBy())) {
			pageable = PageRequest.of(this.getCurrentPage() - 1, this.getPerPage());
		} else {
			pageable = PageRequest.of(
					this.getCurrentPage() - 1,
					this.getPerPage(),
					Sort.by(this.getSortDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, this.getSortBy()));
		}
		return pageable;
	}
}
