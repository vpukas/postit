import { useState, useEffect } from 'react';
import NotesList from './components/NotesList';
import { Routes, Route } from "react-router-dom";
import EditNote from './components/EditNote';
import { BrowserRouter as Router } from "react-router-dom";
const App = () => {

	const [notes, setNotes] = useState([]);

	const addNote = (text) => {
		const newNote = {
			content: text,
		};
		fetch("http://localhost:8080/api/v1/notes", {
			headers: {
				"Content-Type": "application/json",
			},
			method: "post",
			body: JSON.stringify(newNote),
		})
			.then(function (res) {
				if (res.ok) {
					return res.json();
				} else {
					return res.json()
						.then(function (err) {
							alert(err.errors[0]);
							throw new Error("There's an error upstream and it says " + err.errors[0]);
						});
				}
			});
	};


	const deleteNote = (id) => {
		fetch(`http://localhost:8080/api/v1/notes/${id}`, {
			headers: {
				"Content-Type": "application/json",
			},
			method: "delete",
		})
			.then((response) => {
				if (response.status === 200) return response.json();
			})

		window.location.href = `/`;

	};

	return (
		<div className='app'>
			<div className='container'>
				<Router>
					<Routes>
						<Route path='/'
							element={
								<NotesList
									notes={notes}
									handleAddNote={addNote}
									handleDeleteNote={deleteNote}
								/>}
						/>
						<Route path="/edit/:id"
							element={
								<EditNote
									handleDeleteNote={deleteNote}
								/>}
						/>
					</Routes>
				</Router>
			</div>
		</div>
	);
};

export default App;