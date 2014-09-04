package nl.jchmb.agents.media.action;

import nl.jchmb.agents.action.AgentAction;
import nl.jchmb.media.article.Article;

public interface ArticleShareAction extends AgentAction {
	public Article getArticle();
	public void setArticle(Article article);
}