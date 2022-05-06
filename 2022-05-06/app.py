from datetime import datetime

from flask import Flask, jsonify, render_template, request

from pymongo import MongoClient

app = Flask(__name__)

client = MongoClient('mongodb://localhost:27017/')
db = client.timeattack


@app.route('/')
def index():
    return render_template('index.html')


@app.route('/detail/<idx>')
def detail(idx):
    post = db.posts.find_one({'idx': int(idx)}, {'_id': False})

    return render_template('detail.html', post=post)


@app.route('/posts', methods=['GET'])
def get_posts():
    order = request.args.get('order')

    if order == 'asc':
        posts = list(db.posts.find({}, {'_id': False}).sort('read_count'))
    else:
        posts = list(db.posts.find({}, {'_id': False}).sort('read_count', -1))

    return jsonify({'posts': posts})


@app.route('/post', methods=['POST'])
def save_post():
    idx = list(db.posts.find({}).sort('idx', -1))
    title = request.form['title']
    content = request.form['content']
    pw = request.form['pw']
    reg_date = datetime.now().strftime('%Y.%m.%d %H:%M:%S')

    doc = {
        'idx': 0 if len(idx) == 0 else idx[0]['idx'] + 1,
        'title': title,
        'content': content,
        'pw': pw,
        'read_count': 0,
        'reg_date': reg_date
    }

    db.posts.insert_one(doc)

    return jsonify({'result': 'success'})


@app.route('/post', methods=['GET'])
def get_post():
    idx = int(request.args.get('idx'))

    post = db.posts.find_one({'idx': idx}, {'_id': False})
    db.posts.update_one({'idx': idx}, {'$set': {'read_count': post['read_count'] + 1}})

    return jsonify({'post': post})


@app.route('/post', methods=['PUT'])
def update_post():
    idx = int(request.form['idx'])
    title = request.form['title']
    content = request.form['content']

    db.posts.update_one({'idx': idx}, {'$set': {
        'title': title,
        'content': content
    }})

    return jsonify({'result': 'success'})


@app.route('/post', methods=['DELETE'])
def delete_post():
    idx = int(request.args.get('idx'))

    db.posts.delete_one({'idx': idx})

    for row in range(idx + 1, len(list(db.posts.find({}, {'_id': False}))) + 1):
        db.posts.update_one({'idx': row}, {'$set': {'idx': row - 1}})

    return jsonify({'result': 'success'})


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8000)
