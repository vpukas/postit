import React from 'react';
import { useState, useEffect } from 'react';
import { MdDeleteForever } from 'react-icons/md';

const EditNote = ({ handleDeleteNote }) => {
	let noteId = window.location.href.split("/edit/")[1];
	const [note, setNote] = useState({
		id: noteId,
		content: "",
	});
	const characterLimit = 201;

	useEffect(() => {
		fetch(`http://localhost:8080/api/v1/notes/${note.id}`, {
			headers: {
				"Content-Type": "application/json",
			},
			method: "get",
		})
			.then((response) => {
				if (response.status == 200) return response.json();
			})
			.then((noteData) => {
				setNote(noteData);
			});
	}, []);

	const handleChange = (event) => {
		if (characterLimit - event.target.value.length >= 0) {
			updateNote("content", event.target.value);
		}
	};

	const handleSaveClick = () => {
		if (note.content.trim().length > 0) {
			handleUpdateNote(note);
			updateNote("content", '');
			window.location.href = `/`;
		}
	};

	const handleUpdateNote = (note) => {
		fetch(`http://localhost:8080/api/v1/notes/${note.id}`, {
			headers: {
				"Content-Type": "application/json",
			},
			method: "put",
			body: JSON.stringify(note),
		})
			.then(function (res) {
				if (res.ok) {
					return res.json();
					window.location.href = `/`;
				} else {
					return res.json()
						.then(function (err) {
							alert(err.errors[0]);
							throw new Error("There's an error upstream and it says " + err.errors[0]);
						});
				}
			});
			
	};

	function updateNote(prop, value) {
		const newNote = { ...note };
		newNote[prop] = value;
		setNote(newNote);
	}
	return (
		<div className='note new'>
			<textarea
				rows='8'
				cols='10'
				placeholder='Type to add a note...'
				value={note.content}
				onChange={handleChange}
			></textarea>
			<div className='note-footer'>
				<small>
					{characterLimit - note.content.length} Remaining
				</small>
				<button className='save' onClick={handleSaveClick}>
					Save
				</button>
				<MdDeleteForever
					onClick={() => handleDeleteNote(note.id)}
					className='delete-icon'
					size='1.3em'
				/>
			</div>
		</div>
	);
};

export default EditNote;