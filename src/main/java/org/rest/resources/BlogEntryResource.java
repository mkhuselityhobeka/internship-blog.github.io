package org.rest.resources;

import org.core.model.BlogEntry;
import org.springframework.hateoas.ResourceSupport;

public class BlogEntryResource extends ResourceSupport{
		 
		private String title;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public BlogEntry toBlogEntry() {
			 BlogEntry entry = new BlogEntry();
		        entry.setTitle(title);
		        return entry;
		}

}
