package com.example.peoplesinsights.model;

//this is used in VoteRepository to return custom results from the query.
public class ChoiceVoteCount {

	private Long choiceId;
	private Long voteCount;
	
	public ChoiceVoteCount(Long choiceId, Long voteCount) {
        this.choiceId = choiceId;
        this.voteCount = voteCount;
    }
	
	public Long getChoiceId() {
		return choiceId;
	}
	public void setChoiceId(Long choiceId) {
		this.choiceId = choiceId;
	}
	public Long getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(Long voteCount) {
		this.voteCount = voteCount;
	}
	
}
