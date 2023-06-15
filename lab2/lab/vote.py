from fastapi import FastAPI, status
from collections import Counter
from pydantic import BaseModel


class Vote:
    def __init__(self, id: int, value: str):
        self.id = id
        self.value = value


class Poll:
    def __init__(self, id: int, description: str, votes: dict[int, Vote] | None = None):
        self.id = id
        self.description = description
        if votes is None:
            self.votes: dict[int, Vote] = {}
        else:
            self.votes = votes

#example data
polls = {
    1: Poll(1, "test", {
        5: Vote(5, "Yes"),
        3: Vote(3, "No"),
        1: Vote(1, "No")
    }),
    2: Poll(2, "test2", {
        3: Vote(3, "Unsure")
    })
}
app = FastAPI()
hostname = "localhost:8000"


@app.get("/poll")
async def get_polls():
    return {
        "selfLink": f"{hostname}/poll",
        "polls": [f"{hostname}/poll/{poll_id}" for poll_id in polls]
    }


@app.get("/poll/{poll_id}")
async def get_poll(poll_id: int):
    if poll_id not in polls:
        return status.HTTP_404_NOT_FOUND
    return {
        "selfLink": f"{hostname}/poll/{poll_id}",
        "votesTotal": len(polls[poll_id].votes),
        "description": polls[poll_id].description,
        "voteCount": [{
            "vote": vote,
            "count": count
        } for vote, count in Counter(vote.value for vote in polls[poll_id].votes.values()).items()],
        "votesLink": f"{hostname}/poll/{poll_id}/vote"
    }


@app.get("/poll/{poll_id}/vote")
async def get_poll_votes(poll_id: int):
    if poll_id not in polls:
        return status.HTTP_404_NOT_FOUND
    return {
        "selfLink": f"{hostname}/poll/{poll_id}/vote",
        "votes": [
            {
                "value": polls[poll_id].votes[vote_id].value,
                "selfLink": f"{hostname}/poll/{poll_id}/vote/{vote_id}"
            } for vote_id in polls[poll_id].votes
        ]
    }


@app.get("/poll/{poll_id}/vote/{vote_id}")
async def get_poll_vote(poll_id: int, vote_id: int):
    if poll_id not in polls:
        return status.HTTP_404_NOT_FOUND
    if vote_id not in polls[poll_id].votes:
        return status.HTTP_404_NOT_FOUND

    return {
        "selfLink": f"{hostname}/poll/{poll_id}/vote/{vote_id}",
        "value": polls[poll_id].votes[vote_id].value
    }


new_poll_id = 10

class CreatePoll(BaseModel):
    description: str



@app.post("/poll")
async def post_poll(item: CreatePoll):
    global new_poll_id
    poll_id = new_poll_id
    new_poll_id += 1
    poll = Poll(poll_id, description=item.description)
    polls[poll_id] = poll
    return {
        "selfLink": f"{hostname}/poll/{poll_id}"
    }
# class CreateVote(BaseModel):
#     value: str
#
# new_vote_id = 10
#
# @app.post("/poll/{poll_id}/vote")
# async def post_vote(item: CreateVote):
#     global new_vote_id
#     vote_id = new_vote_id
#     new_vote_id += 1