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
    return render_template('detail.html')


@app.route('/post', methods=['GET'])
def get_post():
    posts = list(db.post.find({}, {'_id': False}).sort('reg_date', -1))

    return jsonify({'posts': posts})


@app.route('/post', methods=['POST'])
def save_post():
    idx = list(db.post.find({}).sort('idx', -1))
    title = request.form['title']
    content = request.form['content']
    pw = request.form['pw']
    reg_date = datetime.now().strftime('%Y.%m.%d %H:%M:%S')

    doc = {
        'idx': 0 if len(idx) == 0 else idx[0]['idx'] + 1,
        'title': title,
        'content': content,
        'pw': pw,
        'reg_date': reg_date
    }

    db.post.insert_one(doc)

    return jsonify({'result': 'success'})


@app.route('/post', methods=['DELETE'])
def delete_post():
    idx = int(request.args.get('idx'))

    db.post.delete_one({'idx': idx})

    for row in range(idx + 1, len(list(db.post.find({}, {'_id': False}))) + 1):
        db.post.update_one({'idx': row}, {'$set': {'idx': row - 1}})

    return jsonify({'result': 'success'})


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8000)
