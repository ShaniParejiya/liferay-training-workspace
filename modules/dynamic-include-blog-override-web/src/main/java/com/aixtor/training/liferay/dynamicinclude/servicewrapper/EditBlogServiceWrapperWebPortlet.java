package com.aixtor.training.liferay.dynamicinclude.servicewrapper;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
},
service = ServiceWrapper.class)
public class EditBlogServiceWrapperWebPortlet extends BlogsEntryLocalServiceWrapper {

	private static Log log = LogFactoryUtil.getLog(EditBlogServiceWrapperWebPortlet.class);
	
	public BlogsEntry deleteBlogsEntry(BlogsEntry blogsEntry) {
			log.info("Delete Blogs Entry ");
		return getWrappedService().deleteBlogsEntry(blogsEntry);
	}

	public BlogsEntry deleteBlogsEntry(long entryId) throws PortalException {
		
		log.info("Delete Blogs Entry Using EntryID");
		return getWrappedService().deleteBlogsEntry(entryId);
	}

	public void deleteEntries(long groupId) throws PortalException {
		
		log.info("Delete Entries Using Group ID");
		getWrappedService().deleteEntries(groupId);
	}

	public BlogsEntry deleteEntry(BlogsEntry entry) throws PortalException {
		log.info("Delete Entry");
		return getWrappedService().deleteEntry(entry);
	}

	public void deleteEntry(long entryId) throws PortalException {
		log.info("Delete Entry using entryID");
		getWrappedService().deleteEntry(entryId);
	}

}
