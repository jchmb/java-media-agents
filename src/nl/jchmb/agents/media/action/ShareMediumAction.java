package nl.jchmb.agents.media.action;

import java.util.Collection;

import nl.jchmb.agents.Agent;
import nl.jchmb.agents.action.AgentAction;
import nl.jchmb.media.adapter.MediumAdapter;
import nl.jchmb.media.adapter.exception.MediumAdapterException;
import nl.jchmb.media.article.Article;
import nl.jchmb.utils.filter.AcceptingFilter;
import nl.jchmb.utils.filter.Filter;
import nl.jchmb.utils.selector.Selector;

public abstract class ShareMediumAction implements AgentAction {
	private MediumAdapter<Article> mediumAdapter;
	private Filter<Article, Collection<Article>> articleFilter;
	private Selector<Article, Collection<Article>> articleSelector;
	
	public ShareMediumAction(MediumAdapter<Article> mediumAdapter) {
		this.mediumAdapter = mediumAdapter;
		setArticleFilter(new AcceptingFilter<Article, Collection<Article>>());
		articleSelector = null;
	}
	
	protected abstract ArticleShareAction getShareArticleAction();
	
	public void setArticleFilter(Filter<Article, Collection<Article>> articleFilter) {
		this.articleFilter = articleFilter;
	}
	
	public void setArticleSelector(Selector<Article, Collection<Article>> articleSelector) {
		this.articleSelector = articleSelector;
	}
	
	@Override
	public void execute(Agent agent) {
		if (mediumAdapter != null && agent != null) {
			try {
				Collection<Article> articles = mediumAdapter.fetch();
				Collection<Article> filteredArticles = articleFilter.applyFilter(articles);
			} catch (MediumAdapterException e) {
				e.printStackTrace();
			}
		}
	}
}